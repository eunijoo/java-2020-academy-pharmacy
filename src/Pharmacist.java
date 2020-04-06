
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

import com.util.DBConn;

public class Pharmacist {
	private Scanner sc = new Scanner(System.in);
	private ProductDAO dao = new ProductDAOImpl();
	/*
	 *private PharmacistDAO dao = new PharmacistDAOImpl(); 
	 *		> �ñ��� ��. 
	 *				-  PharmacistDAO�� �������̽��� �����ϰ�, �װ��� �����Ѱ��� PharmacistDAOImpl�ε�,
	 *				-  private PharmacistDAO dao = new PharmacistDAOImpl(); �̷��� �ν��Ͻ�ȭ ����?
	 */
	
	
	public void pharmacistManage() {
		int ch;
		while(true) {
			System.out.println("\n ���");
			do {
				System.out.println("1.������  2. �Ǹ���Ȳ  3.ó��   4.�α׾ƿ� =>");
				ch = sc.nextInt();
			} while (ch<1 || ch>4);
			
			if(ch==4) break;
			
			switch (ch) {
			case 1: inventory();break;
			case 2: sales();break;
			case 3: prescription();break;

			}
		}
	}
	
	public void inventory() {
		int ch;
		
		while(true) {
			System.out.println("\n ���");
			do {
				System.out.println("1.�԰��� 2.��ǰ���� 3.��ǰ����  4.�������ȳ� 5.����=>");
				ch = sc.nextInt();
			} while (ch<1 || ch>5);
			
			if(ch==5) break;
			
			switch (ch) {
			case 1: insert();break;
			case 2: update();break;
			case 3: delete();break;
			case 4: listproduct();break;
			}
		}
		
	}
	
	public void insert() {
		System.out.println("\n �԰� ���..."); //����ũ�� �ռҵ��� �԰� ��� 
		
		ProductDTO dto = new ProductDTO();
		
		
		System.out.println();
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}
	
	public void listproduct() {
		
	}
	
	
	public void sales() {
		int ch;
		
		while(true) {
			System.out.println("\n ���");
			do {
				System.out.println("1.�մԺ� �Ǹ���Ȳ 2.ǰ�� �ǸŸ���Ʈ  3.�� �ǸŽ��� 4.����=>");
				ch = sc.nextInt();
			} while (ch<1 || ch>4);
			
			if(ch==4) break;
			
			switch (ch) {
			case 1: customerlist();break;
			case 2: productlist();break;
			case 3: totalsales();break;
			
			}
		}
	}
	


	public void customerlist() {
		
	}
	
	public void totalsales() {
		
	}
	
	private void productlist() {
		
		
	}
	
	public void prescription() {
		System.out.print("\n ���� �Է� >");
		int pnum;
		int ch;
		
		String symptom = sc.next();
		
		if(symptom.toLowerCase().startsWith("�Ӹ�")) {
			
			do {
				System.out.println("1.����-Ÿ�̷��� 2.����-�Ժ��� =>");
				ch = sc.nextInt();
			} while (ch<1 || ch>2);
			
			switch (ch) {
			case 1: 
					pnum = 3; 
					dao.deleteProduct(pnum);
					System.out.println("Ÿ�̷��� �Ǹ� �Ϸ�");
					break; //Ÿ�̷��� ��� ����
			case 2: 
					pnum = 4; 
					dao.deleteProduct(pnum);
					System.out.println("�Ժ��� �Ǹ� �Ϸ�");
					break; //�Ժ��� ��� ����
			}
			
			

		}else if(symptom.toLowerCase().startsWith("�Ǻ�")) {
			
			do {
				System.out.println("1.ö����-�Ľõ� 2.�Ǻ�ȸ��-�޵��� =>");
				ch = sc.nextInt();
			} while (ch<1 || ch>2);
			
			switch (ch) {
			case 1: 
					System.out.println("�Ľõ� ��� ���� ");
					pnum = 5; 
					dao.deleteProduct(pnum); 
					break; 
			case 2: 
					System.out.println("�޵��� ��� ���� ");
					pnum = 6; 
					dao.deleteProduct(pnum); 
					break; 
			}
			

		}else if(symptom.toLowerCase().startsWith("���帧")) {
			
			do {
				System.out.println("1.���帧 ����-�뽺ī���� 2.���帧 ����-��īũ�� =>");
				ch = sc.nextInt();
			} while (ch<1 || ch>2);
			
			switch (ch) {
			case 1: 
					System.out.println("�뽺ī���� ��� ���� ");
					pnum = 7; 
					dao.deleteProduct(pnum); 
					break; 
			case 2: 
					System.out.println("��īũ�� ��� ���� ");
					pnum = 8; 
					dao.deleteProduct(pnum); 
					break; 
			}

		}
		
		
		/*
		int ch;
		
		while(true) {
			System.out.println("\n ���");
			do {
				System.out.println("1.�մԺ� �Ǹ���Ȳ 2.ǰ�� �ǸŸ���Ʈ  3.�� �ǸŽ��� 4.����=>");
				ch = sc.nextInt();
			} while (ch<1 || ch>4);
			
			if(ch==4) break;
			
			switch (ch) {
			case 1: customerlist();break;
			case 2: productlist();break;
			case 3: totalsales();break;
			
			}
		}
		*/
		
		
		
	}


}

