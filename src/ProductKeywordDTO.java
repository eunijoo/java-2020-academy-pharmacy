
public class ProductKeywordDTO {
	private String keyword;
	private ProductDTO product;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public String toString() {
		return "[��ǰ��ȣ: " + product.getPnum() + " | ��ǰ��: " + product.getPname() + " | ����: " + product.getPrice()
				+ "�� |  ���: " + product.getStock() + "��]";
	}

}
