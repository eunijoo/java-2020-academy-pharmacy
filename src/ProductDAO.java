

import java.util.List;
import java.util.Map;

public interface ProductDAO {
	public int insertProduct(ProductDTO dto); //��ǰ�߰�-�԰���
	public int updateProduct(ProductDTO dto); //��ǰ����-��ǰ����
	//public int deleteProduct(ProductDTO dto); //��ǰ����-��ǰ����
	public int deleteProduct(int pnum); //��ǰ���� �� , �̸��� ����?
	public List<ProductDTO> listStock();//�������ȳ�
	
	
	//public ProductDTO readProduct(String rrn); //�ֹι�ȣ�� �ǸŸ���Ʈ - �̸� !
	//public List<ProductDTO> listProduct(Map<String, Object> map);//�մԺ� �Ǹ���Ȳ
	public Map<String, Integer> listCustomer(); //�մԺ� �Ǹ���Ȳ-com>score3>ScoreDAO
	public List<ProductDTO> listProduct(String pname); //ǰ�� �ǸŸ���Ʈ
	public List<ProductDTO> listProduct();  //��ü �ǸŸ���Ʈ -�� �ǸŽ���
	
	//ó��
	//���ܱ��
	//��ǰ����. - �Ѱ� 

	
}
