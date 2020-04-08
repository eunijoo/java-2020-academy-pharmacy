import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Pharmacist {
	public static final String KEY_ID = "id";
	public static final String KEY_PW = "pw";
	public static final String ADMIN_ID = "admin";
	public static final String ADMIN_PW = "1234";
	private Scanner sc = new Scanner(System.in);
	private ProductDAO dao = new ProductDAOImpl();
	private boolean loggedIn = false;
	private Map<String, String> administrator = new HashMap<String, String>();
	/*
	 * private PharmacistDAO dao = new PharmacistDAOImpl(); > �ñ��� ��. -
	 * PharmacistDAO�� �������̽��� �����ϰ�, �װ��� �����Ѱ��� PharmacistDAOImpl�ε�, - private
	 * PharmacistDAO dao = new PharmacistDAOImpl(); �̷��� �ν��Ͻ�ȭ ����?
	 */

	public Pharmacist() {
		administrator.put(KEY_ID, ADMIN_ID);
		administrator.put(KEY_PW, ADMIN_PW);
	}

	public void pharmacistManage() {
		int ch;
		try {
			while (true) {
				System.out.println("\n ��� [������ ���]");
				String id;
				String pwd;
				if (!loggedIn) {
					do {
						System.out.println("BUT.... �α����� �ʿ��մϴ�.");
						System.out.print("���̵�:");
						id = sc.next();
						System.out.print("��й�ȣ:");
						pwd = sc.next();
						if (id.equals(administrator.get(KEY_ID)) && pwd.equals(administrator.get(KEY_PW))) {
							System.out.println("�����ڷ� �α����Ͽ����ϴ�.");
							loggedIn = true;
						} else {
							System.out.println("���̵� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
							return;
						}
					} while (!loggedIn);// �α����� ���� ���� ��쿡 ��� ��ȸ
				}
				do {
					System.out.print("1.������  2. �Ǹ���Ȳ  3.ó��   4.�α׾ƿ� =>");
					ch = sc.nextInt();
				} while (ch < 1 || ch > 4);
				if (ch == 4) {
					loggedIn = false;
					break;
				}
				switch (ch) {
				case 1:
					inventory();
					break;
				case 2:
					sales();
					break;
				case 3:
					prescription();
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inventory() {
		int ch;

		while (true) {

			do {
				System.out.println("1.�԰��� 2.��ǰ���� 3.��ǰ����  4.����Ʈ 5.����=>");
				ch = sc.nextInt();
			} while (ch < 1 || ch > 5);

			if (ch == 5)
				break;

			switch (ch) {
			case 1:
				insert();
				break;
			case 2:
				update();
				break;
			case 3:
				delete();
				break;
			case 4:
				sales();
				break;

			}

		}
	}

//�԰�	
	public void insert() {
		System.out.println("\n �԰� ���..."); // ����ũ�� �ռҵ��� �԰� ���

		InputDTO dto = new InputDTO();
		try {
			printProducts(dao.listProduct());
			System.out.print("��ǰ ��ȣ? ");
			dto.setPnum(sc.nextInt());
			System.out.print("�԰� ��¥? ");
			dto.setIdate(sc.next());
			System.out.println("���� ? ");
			dto.setIqty(sc.nextInt());

			int result = dao.insertProduct(dto);
			if (result != 0)
				;
			System.out.println("�԰��ȣ " + result + "�� ����� �Ϸ� �Ǿ����ϴ�.");
			pharmacistManage();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();

	}

	// ��ǰ����
	public void update() {
		System.out.println("��ǰ ���� ... ");
		List<InputListDTO> list = dao.listStock();
		for (InputListDTO Ip : list) {
			System.out.print("�԰��ȣ:  " + Ip.getInum() + "\t");
			System.out.print("��ǰ��ȣ:  " + Ip.getPnum() + "\t");
			System.out.print("��ǰ��:  " + Ip.getPname() + "\t");
			System.out.print("�԰���� : " + Ip.getIqty() + "\t");
			System.out.print("���:  " + Ip.getStock() + "\n");
		}
		try {
			InputListDTO dto = new InputListDTO();
			System.out.print("���� �� �԰��ȣ?");
			dto.setInum(sc.nextInt());
			InputDTO input = dao.readInput(dto.getInum());
			if (input == null) {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				return;
			}
			System.out.print("�԰� ��¥?");
			dto.setIdate(sc.next());
			System.out.print("����?");
			int beforeIqty = input.getIqty();
			dto.setIqty(sc.nextInt());
			if (beforeIqty >= dto.getIqty()) {
				System.out.println("�������� ���� �������� ũ�� �Է��ؾ� �մϴ�.");
				return;
			}
			int result = dao.updateProduct(dto);
			if (result != 0) {
				System.out.println("���� �Ϸ�.");
			} else {
				System.out.println("���� ����");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��ǰ����
	public void delete() {
		System.out.println("[ ��ǰ ���� ]");
		List<InputListDTO> list = dao.listStock();
		for (InputListDTO Ip : list) {
			System.out.print("�԰��ȣ:  " + Ip.getInum() + "\t");
			System.out.print("��ǰ��ȣ:  " + Ip.getPnum() + "\t");
			System.out.print("��ǰ��:  " + Ip.getPname() + "\t");
			System.out.print("�԰���� : " + Ip.getIqty() + "\t");
			System.out.print("���:  " + Ip.getStock() + "\n");
		}
		int inum;
		try {
			InputDTO dto = new InputDTO();
			System.out.print("������ �԰� ��ȣ?");
			inum = sc.nextInt();
			int result = dao.deleteProduct(inum);
			if (result != 0) {
				System.out.println(result + "�� ��ǰ�� ���� �߽��ϴ�.");
			} else {
				System.out.println("���� ����.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sales() {
		int ch;
		while (true) {
			System.out.println("\n ���");
			do {
				System.out.println("1.�մԺ� ����ũ �Ǹ���Ȳ 2.��ü �ǸŸ���Ʈ  3.����=>");
				ch = sc.nextInt();
			} while (ch < 1 || ch > 3);
			if (ch == 3)
				break;
			switch (ch) {
			case 1:
				customerlist();
				break;
			case 2:
				productlist();
				break;

			}
		}
	}

	// �����Ʈ
	public void liststock() {
		System.out.println("��ǰ ��� ����Ʈ");
		List<InputListDTO> list = dao.listStock();
		for (InputListDTO dto : list) {
			System.out.print("�԰��ȣ:  " + dto.getInum() + "\t");
			System.out.print("��ǰ��ȣ:  " + dto.getPnum() + "\t");
			System.out.print("��ǰ��:  " + dto.getPname() + "\t");
			System.out.print("���:  " + dto.getStock() + "\n");
		}
	}

	// �մԺ� ����ũ�ǸŸ���Ʈ
	public void customerlist() {
		System.out.println("\n�մԺ� ����ũ �ǸŸ���Ʈ...");
		String rrn;
		System.out.println("�˻��� �մ�?");
		rrn = sc.next();
		List<SaleSumDTO> list = dao.listCustomer(rrn);

		System.out.println("�̸�\t ���ų�¥\t\t �Ǹ�ǰ��\t\t�Ǹż���");

		for (SaleSumDTO dto : list) {
			System.out.print(dto.getcName() + "\t");
			System.out.print(dto.getSdate() + "\t");
			System.out.print(dto.getpName() + "\t");
			System.out.print(dto.getsQty() + "\n");
		}
	}

//��ü �ǸŸ���Ʈ
	private void productlist() {
		System.out.println("\nǰ�� �ǸŸ���Ʈ...");
		List<SaleSumDTO> list = dao.listSumProduct();

		for (SaleSumDTO dto : list) {
			System.out.print(dto.getpName() + "\t");
			System.out.print(dto.getpNum() + "\t");
			System.out.print(dto.getsQty() + "\t");
			System.out.print(dto.getSdate() + "\n");
		}
	}

//ó��

	public void prescription() {
		int ch;
		try {
			while (true) {
				do {
					System.out.print("1.ó���ϱ� 2.ó����� 3.�ڷΰ��� > ");
					ch = sc.nextInt();
				} while (ch < 1 || ch > 3);
				switch (ch) {
				case 1:
					prescribe();
					break;
				case 2:
					managePrescription();
					break;
				}
				if (ch == 3) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void prescribe() {
		int result; // ���� ó�����
		int choice, qty; // ���ù�ȣ, ����
		String keyword; // �˻���
		List<ProductDTO> list = null;
		try {
			System.out.print("\n ���� �Է� > ");
			keyword = sc.next().trim();
			if (keyword == null || keyword.length() == 0) {
				System.out.println("Ű���带 �Է��ϼ���...");
				return;
			}
			list = dao.searchKeyword(keyword);
			if (list == null || list.size() == 0) {
				System.out.println("�˻� ����� �����ϴ�.");
				return;
			}
			for (int i = 0; i < list.size(); i++) {
				// ���� ��ǰ ���
				System.out.println(i + 1 + "��. " + list.get(i).toString());
			}
			System.out.print("\n ó���� �� ��� ��ȣ�� ���� > ");
			choice = sc.nextInt();
			if (choice < 1 || choice > list.size()) {
				System.out.println("�ùٸ� ��ȣ�� �Է��ϼ���.");
				return;
			}
			System.out.print("���� �Է� ? ");
			qty = sc.nextInt();
			result = dao.insertSale(list.get(choice - 1).getPnum(), qty);
			if (result != 0) {
				System.out.println("ó��Ǿ����ϴ�. �����ޣ��ޣ���");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void managePrescription() {
		int ch;
		int result;
		String keyword;
		int pnum;
		System.out.print("1.������ 2.�����߰� 3.������� 4.�ڷΰ���> ");
		ch = sc.nextInt();
		if (ch == 4) {
			return;
		} else if (ch < 1 || ch > 4) {
			System.out.println("�ùٸ� ��ȣ�� �Է��� �ּ���...");
			return;
		}
		List<String> keywords = dao.getKeywords();
		List<ProductKeywordDTO> list = null;
		printSymtoms(keywords);
		switch (ch) {
		case 1:// ���� ���
			if (keywords == null || keywords.size() == 0) {
				System.out.println("��ϵ� ���� ����� �����ϴ�. ���� �߰��� �̿��Ͽ� ����� �ּ���...");
				return;
			}
			System.out.println("��ϵ� ���� ����� ������ �����ϴ�...");
			break;
		case 2:// ���� �߰�
			System.out.println("������ ��ϵ� ���� �׸��� ������ �����ϴ�. ");
			System.out.print("�߰��� ����(�ְ���) ? ");
			keyword = sc.next();
			printProducts(dao.listProduct());
			System.out.print("�߰��� ��ǰ��ȣ ? ");
			pnum = sc.nextInt();
			result = dao.insertKeyword(pnum, keyword);
			if (result != 0) {
				System.out.println("������ ���������� ��ϵǾ����ϴ�.");
			} else {
				System.out.println("�̹� ��ϵǾ����ϴٸ�...?");
			}
			break;
		case 3:// ���� ����
			System.out.print("������ ����? ");
			keyword = sc.next();
			list = dao.listByKeyword(keyword);
			printSymtomObjects(list);
			System.out.print("������ ��ǰ��ȣ ? ");
			pnum = sc.nextInt();
			result = dao.deleteKeywordProduct(pnum, keyword);
			if (result != 0) {
				System.out.println("������ ���������� �����Ǿ����ϴ�.");
			}

			break;
		}
	}

	public void printSymtoms(List<String> keywords) {
		if (keywords == null || keywords.size() == 0) {
			return;
		}
		int index = 0;
		for (String k : keywords) {
			System.out.println(++index + ". " + k);
		}
	}

	public void printSymtomObjects(List<ProductKeywordDTO> list) {
		if (list == null || list.size() == 0) {
			return;
		}

		for (ProductKeywordDTO dto : list) {
			System.out.println(dto);
		}
	}

	public void printProducts(List<ProductDTO> list) {
		if (list == null || list.size() == 0) {
			return;
		}
		for (ProductDTO dto : list) {
			System.out.println(dto);
		}

	}
}
