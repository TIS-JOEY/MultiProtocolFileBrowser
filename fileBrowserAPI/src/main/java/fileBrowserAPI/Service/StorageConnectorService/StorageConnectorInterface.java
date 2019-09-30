package fileBrowserAPI.Service.StorageConnectorService;

public interface StorageConnectorInterface {
	public boolean connect(String host, int port, String username, String password);
	public boolean closeConnection();
	public Object getOperator();
}
