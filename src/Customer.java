import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

public class Customer {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	CustomerDAO dao = new CustomerDAOImpl();
	public void enter() {
		int ch;
		try {
			while(true) {
				do {
					System.out.print("1.����ũ���ſ���Ȯ�� 2.���� 3.��� > ");
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
		int result = 0;
		try {
			int ch;
			CustomerDTO dto = identifyCustomer();
			System.out.print("1.����ũ 2.�ռҵ��� > ");
			ch = Integer.parseInt(br.readLine());
			switch(ch) {
			case 1:
				result = dao.insertSale(ch, "2020-04-05", 2, dto.getcNum());
				break;
			case 2:break;
			}
			if(result>=1) {				
				System.out.println("{{{(>_<)}}} ���Ű� �Ϸ�Ǿ����ϴ�");
			}else {
				System.out.println("������ ���� ���ſ� �����Ͽ����ϴ�...");
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CustomerDTO identifyCustomer() {
		CustomerDTO dto = null;
		try {
			String rrn, name;
			System.out.print("�ֹι�ȣ ? ");
			//ȸ�������� ��ϵ� �ִ��� �˻�
			rrn = br.readLine();
			if(rrn==null || rrn.length()==0) {
				rrn = "011009-3012345";
			}
			dto = dao.readCustomer(rrn);
			if(dto==null) {
				//ȸ���� ��ϵ��� �ʾ�����?
				System.out.print("�̸� ? ");
				name = br.readLine();
				//������ ȸ������ ���
			}else {
				name = dto.getcName();
				System.out.println(name + "�� �ȳ��ϼ���.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
