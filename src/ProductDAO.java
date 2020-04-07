
import java.util.List;

public interface ProductDAO {
	public int insertProduct(InputDTO dto); //��ǰ�߰�-�԰���
	public int updateProduct(InputDTO dto); //��ǰ����-��ǰ����
	public int deleteProduct(int inum); //��ǰ����-��ǰ����
	public List<ProductDTO> listStock();//�������ȳ�
	//public Map<String, Integer> listStock(); 
	
	
	//public ProductDTO readProduct(String rrn); //�ֹι�ȣ�� �ǸŸ���Ʈ - �̸� !
	//public List<ProductDTO> listProduct(Map<String, Object> map);//�մԺ� �Ǹ���Ȳ
	public List<SaleSumDTO> listCustomer(String rrn); //�մԺ� �Ǹ���Ȳ-com>score3>ScoreDAO
	public List<SaleSumDTO> listProduct(); //�� �ǸŸ���Ʈ
	
	//ó��
	public List<ProductDTO> searchKeyword(String keyword); //ó��˻�
	public int insertSale(int pnum, int qty);
	//���� ���
	public List<String> getKeywords(); //���� Ű���常 ��� (�ߺ�����)
	public List<ProductKeywordDTO> listByKeyword(String keyword); //���� ��ϵ� ���
	public int insertKeyword(int pnum, String keyword);
	public int deleteKeywordProduct(int pnum, String keyword)
	//���� �߰�
	
	//���� ����

	//���� ����
	//��ǰ����.
	
}

