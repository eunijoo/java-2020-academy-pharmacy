
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.util.DBConn;

public class ProductDAOImpl implements ProductDAO {
	private Connection conn = DBConn.getConnection();

	@Override
	public int insertProduct(ProductDTO dto) {
		int result = 0;
		Statement stmt = null;

		return result;
	}

	@Override
	public int updateProduct(ProductDTO dto) {

		return 0;
	}
	/*
	 * @Override public int deleteProduct(ProductDTO dto) {
	 * 
	 * return 0; }
	 */

	/*
	 * @Override public ProductDTO readProduct(String rrn) {
	 * 
	 * return null; }
	 */

	@Override
	public List<ProductDTO> listProduct(String pname) {

		return null;
	}

	@Override
	public List<ProductDTO> listProduct() {

		return null;
	}

	/*
	 * @Override public List<ProductDTO> listProduct(Map<String, Object> map) { //
	 * TODO Auto-generated method stub return null; }
	 */

	@Override
	public List<ProductDTO> listStock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> listCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	// ��� ����
	@Override
	public int deleteProduct(int pnum) {
		// > �ñ��� ��? pnum(ǰ���ȣ),stock(���)�� �Է¹޾Ƽ� �����ؾ߰���??

		PreparedStatement pstmt = null;
		String sql;
		int result = 0;

		try {
			//sql = "DELETE FROM product WHERE pnum=?";
			//> pnum�� �޾� ���������� ������Ѵ�.
			//> sale(�Ǹ�) ���̺��� 
			sql = "UPDATE SET stock = stock-1 WHERE  pnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pnum);
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return 0;
	}

}
