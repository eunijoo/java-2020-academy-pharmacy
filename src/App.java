import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Customer customer = new Customer();
		try {
			int ch;
			while (true) {
				do {
					System.out.print("1.����� 2.��� 3.���� > ");
					ch = sc.nextInt();
				} while (ch < 1 || ch > 3);
				if (ch == 3) {
					break;
				}
				switch (ch) {
				case 1:
					// �մ�
					customer.enter();
					break;
				case 2:
					// ���
					
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}
