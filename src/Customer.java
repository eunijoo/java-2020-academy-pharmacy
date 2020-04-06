import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;

public class Customer {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	CustomerDAO dao = new CustomerDAOImpl();
	CustomerDTO dto = null;

	public void customer() {
		int ch;
		try {
			while (true) {
				System.out.println("\n[��]");
				do {
					System.out.print("1.����ũ ���� ���� ���� Ȯ�� 2.���� 3.���[����] 4.���� => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 4);

				if (ch == 3)
					break;
				if (ch == 4)
					return;

				switch (ch) {
				case 1:
					check();
					break;
				case 2:
					purchase();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void check() {
		System.out.println("\n����ũ ���� ���� ���� Ȯ��");
		CustomerDTO dto = new CustomerDTO();
		String name, rrn;

		try {
			System.out.print("�̸� > ");
			name = br.readLine();

			System.out.print("�ֹι�ȣ [- �Է�] > ");
			rrn = br.readLine();

			if (rrn.length() != 14) {
				System.out.println("��ȿ�������� �ֹι�ȣ �Դϴ�.");
				return;
			}
			dto = dao.checkAvailability(rrn);
			System.out.println(name + "���� ����ũ ���� ������ ��¥��");
			System.out.println("�� " + dto.getRrn() + " ���Դϴ�.");

			String date = dto.getRrn();

			if (date.equals(dao.checkDate())) {
				System.out.println("������ " + dao.checkDate() + "�� ����ũ ���� �����մϴ�.");
				System.out.println("�����Ͻðڽ��ϱ�?");
				int ch;
				while (true) {
					do {
						System.out.print("1.�� 2.�ƴϿ�");
						ch = Integer.parseInt(br.readLine());
					} while (ch < 1 || ch > 2);
					if (ch == 2) {
						break;
					}
					switch (ch) {
					case 1:
						System.out.println("���� �ҽ��ڵ� �Է�");
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	///
	public boolean checkPurchase() {// ���ſ��� Ȯ��
		try {
			String name, rrn;
			System.out.print("�̸� ? ");
			name = br.readLine();
			System.out.print("�ֹι�ȣ ? ");
			rrn = br.readLine();
			if (!isValidRRN(rrn)) {
				throw new Exception("�ֹε�Ϲ�ȣ ���Ŀ� ���� �ʽ��ϴ�");
			}
			// ȸ����ϵǾ��ִ� ���� �ƴϸ�? �̷��� �����ϱ� �����ϴ�!
			// �ֹε�Ϲ�ȣ�� �������� ������ �߰��� ������ ����
			if (rrn.length() == 13 && isNumber(rrn)) {
				rrn = rrn.substring(0, 7) + "-" + rrn.substring(6);
				System.out.println(rrn);
			}
			dto = dao.readCustomer(rrn);
			if (dto == null) {
				System.out.println("���� �౹���� ����ũ�� �����Ͻ� �̷��� �����Ƿ� ���Ű� �����մϴ�.");
				dto = new CustomerDTO(-1, name, rrn);
			}
			// ȸ������� �� ��� �Լ��� ���Ͽ� ȣ������
			int qty = dao.checkPurchaseMask(dto);
//			System.out.println(qty);
			if (qty > 0) {
				System.out.println("�̹��ֿ� "+qty + "�� ���Ű� �����մϴ�.");
				return true;
			} else {
				switch (qty) {
				case -20011:
					System.out.println("������ ���� ����� �ƴϽʴϴ�.");
					return false;
				case -20021:
					System.out.println("�̹� �̹��ֿ� �����ϼ����Ƿ� ���Ű� �Ұ����մϴ�.");
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void purchase() {
		int result = 0;
		try {
			int ch, qty;
			System.out.print("1.����ũ 2.�ռҵ��� > ");
			ch = Integer.parseInt(br.readLine());
			System.out.print("����? ");
			qty = Integer.parseInt(br.readLine());
			switch (ch) {
			case 1:
				if (dto != null) {
					System.out.print(dto.getcName() + "���� �½��ϱ�? 1.�� 2.�ƴϿ� > ");
					ch = Integer.parseInt(br.readLine());
					if (ch == 2) {
						dto = null;
						checkPurchase();
					}
				} else {
					checkPurchase();
				}
				result = dao.insertSale(dto, ch, qty);
				break;
			case 2:
				break;
			}
			System.out.println(qty+"�� ���Ÿ� �õ��մϴ�.");
			if (result >= 1) {
				System.out.println("{{{(>_<)}}} ���Ű� �Ϸ�Ǿ����ϴ�");
			} else {
				System.out.println("������ ���� ���ſ� �����Ͽ����ϴ�...");
			}
			System.out.println();
		} catch (NumberFormatException e) {
			System.out.println("�ùٸ� ���ڸ� �Է��ϼ���\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isValidRRN(String rrn) {
		// �ֹε�Ϲ�ȣ ��ȿ�� �˻�
		int year, month, day;
		int endDayOfMonth;
		try {
			// 1. ���ڸ� 6�ڸ� + ���ڸ� 7�ڸ� = 13 Ȥ�� ������ �����Ͽ� 14�ڸ����� �˻�
			if (rrn.length() < 13 || rrn.length() > 14) {
				System.out.println("�ֹε�Ϲ�ȣ �ڸ��� ����");
				return false;
			}
			year = Integer.parseInt(rrn.substring(0, 2));
			month = Integer.parseInt(rrn.substring(2, 4));
			day = Integer.parseInt(rrn.substring(4, 6));
			if (month < 1 || month > 12) {
				System.out.println("�� ����" + month);
				return false;
			}
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, 1);
			endDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			if (day < 1 || day > endDayOfMonth) {
				System.out.println("��" + day + "..." + endDayOfMonth);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public CustomerDTO identifyCustomer() {
		CustomerDTO dto = null;
		try {
			String rrn, name;
			System.out.print("�ֹι�ȣ ? ");
			// ȸ�������� ��ϵ� �ִ��� �˻�
			rrn = br.readLine();
			if (rrn == null || rrn.length() == 0) {
				rrn = "011009-3012345";
				System.out.println(rrn + " �ڵ��Է�");
			}
			if (!isValidRRN(rrn)) {
				throw new Exception("�ֹε�Ϲ�ȣ ���Ŀ� ���� �ʽ��ϴ�.");
			}
			// �ֹε�Ϲ�ȣ�� �������� ������ �߰��� ������ ����
			if (rrn.length() == 13 && isNumber(rrn)) {
				rrn = rrn.substring(0, 7) + "-" + rrn.substring(6);
				System.out.println(rrn);
			}
			dto = dao.readCustomer(rrn);
			if (dto == null) {
				// ȸ���� ��ϵ��� �ʾ�����?
				System.out.print("�̸� ? ");
				name = br.readLine();

				// ������ ȸ������ ���
				dao.insertCustomer(new CustomerDTO(Integer.MIN_VALUE, name, rrn));
			} else {
				name = dto.getcName();
				System.out.println(name + "�� �ȳ��ϼ���.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public static boolean isNumber(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
