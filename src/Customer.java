import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Customer {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void enter() {
		int ch;
		try {
			while(true) {
				do {
					System.out.print("1.����ũ���ſ���Ȯ�� 2.���� 3.���");
					ch =  Integer.parseInt(br.readLine());					
				}while(ch<1||ch>3);
				if(ch==3) {
					break;
				}
				switch(ch) {
				case 1://����ũ���ſ���Ȯ��
					checkPurchase();
					break;
				case 2://����
					purchase();
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkPurchase() {// ���ſ��� Ȯ��
		try {
			String rrn;
			System.out.print("�ֹι�ȣ ? ");
			rrn = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void purchase() {
		try {
			int ch;
			System.out.print("1.����ũ 2.�ռҵ��� > ");
			ch = Integer.parseInt(br.readLine());
			System.out.println("{{{(>_<)}}} ���Ű� �Ϸ�Ǿ����ϴ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void identifyCustomer() {
		try {
			String rrn, name;
			System.out.print("�ֹι�ȣ ? ");
			//ȸ�������� ��ϵ� �ִ��� �˻�
			
			if(true==true) {
				//ȸ���� ��ϵ��� �ʾ�����?
				System.out.print("�̸� ? ");
				name = br.readLine();
				//������ ȸ������ ���
			}else {
				name ="ȫ�浿";
				System.out.println("~�� �ȳ��ϼ���.");
				//���� ó�� 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
