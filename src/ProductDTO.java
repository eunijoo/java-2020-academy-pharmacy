

public class ProductDTO {
	private int pnum;		//ǰ���ȣ(�⺻Ű)
	private String pname;	//ǰ���
	private int price;	//�ܰ�
	private int stock;		//������
	
	@Override
	public String toString() {
		return "[��ǰ��ȣ: " + pnum + " | ��ǰ��: " + pname + " | ����: " + price + "�� |  ���: " + stock + "��]";
	}

	
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
}
