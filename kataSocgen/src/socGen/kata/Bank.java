package socGen.kata;



public interface Bank {

	public void addClient(String name, Account account);

	public void addClient(String name);

	public void removeClient(String name);

	public boolean ClientExist(String name);

}
