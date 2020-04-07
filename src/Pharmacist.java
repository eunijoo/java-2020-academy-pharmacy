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
		while (true) {
			System.out.println("\n ���");
			do {
				System.out.println("1.������  2. �Ǹ���Ȳ  3.ó��   4.�α׾ƿ� =>");
				ch = sc.nextInt();
			} while (ch < 1 || ch > 4);

			if (ch == 4)
				break;

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
	}

	public void inventory() {
		int ch;

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
					}
				} while (!loggedIn);//�α����� ���� ���� ��쿡 ��� ��ȸ
			}

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
			System.out.print("��ǰ ��ȣ? [1.����ũ 2.�ռҵ���]");
			dto.setPnum(sc.nextInt());
			System.out.print("�԰� ��¥?");
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

//��ǰ����	
	public void update() {
		System.out.println("��ǰ ���� ... ");
		InputDTO dto = new InputDTO();

		try {
			System.out.print("���� �� �԰��ȣ?");
			dto.setInum(sc.nextInt());

			System.out.print("��ǰ ��ȣ?");
			dto.setPnum(sc.nextInt());

			System.out.print("�԰� ��¥?");
			dto.setIdate(sc.next());

			System.out.print("����?");
			dto.setIqty(sc.nextInt());

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

//��ǰ����	
	public void delete() {
		System.out.println("[ ��ǰ ���� ]");
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
				System.out.println("1.�����Ʈ 2.�մԺ� �Ǹ���Ȳ 3.��ü �ǸŸ���Ʈ  4.����=>");
				ch = sc.nextInt();
			} while (ch < 1 || ch > 4);

			if (ch == 4)
				break;

			switch (ch) {
			case 1:
				liststock();
				break;
			case 2:
				customerlist();
				break;
			case 3:
				productlist();
				break;
			}
		}
	}

//�����Ʈ	
	public void liststock() {
		System.out.println("��ǰ ��� ����Ʈ");

		List<ProductDTO> list = dao.listStock();
		for (ProductDTO dto : list) {
			System.out.print("��ǰ��ȣ:  " + dto.getPnum() + "\t");
			System.out.print("��ǰ��:  " + dto.getPname() + "\t");
			System.out.print("���:  " + dto.getStock() + "\n");
		}

	}

//�մԺ� �ǸŸ���Ʈ
	public void customerlist() {
		System.out.println("\n�մԺ� �ǸŸ���Ʈ...");
		String rrn;
		System.out.println("�˻��� �մ�?");
		rrn = sc.next();
		List<SaleSumDTO> list = dao.listCustomer(rrn);

		for (SaleSumDTO dto : list) {
			System.out.println(dto.getcName() + "\t");
			System.out.print(dto.getSdate() + "\t");
			System.out.print(dto.getpName() + "\t");
			System.out.print(dto.getsQty() + "\n");
		}
	}

//��ü �ǸŸ���Ʈ
	private void productlist() {
		System.out.println("\nǰ�� �ǸŸ���Ʈ...");
		List<SaleSumDTO> list = dao.listProduct();

		for (SaleSumDTO dto : list) {
			System.out.print(dto.getpName() + "\t");
			System.out.print(dto.getpNum() + "\t");
			System.out.print(dto.getsQty() + "\t");
			System.out.print(dto.getSdate() + "\n");
		}
	}

//ó��

	public void prescription() {
		int result; // ���� ó�����
		int choice, qty; // ���ù�ȣ, ����
		String keyword; // �˻���
		List<ProductDTO> list = null;
		try {
			System.out.println("\n ���� �Է� >...");
			keyword = sc.next().trim();
			if (keyword == null || keyword.length() == 0) {
				System.out.println("Ű���带 �Է��ϼ���...");
				return;
			}
			list = dao.searchKeyword(keyword);
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

}
