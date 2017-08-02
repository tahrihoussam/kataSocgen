package socGen.kata;



public class Main {
	public static void main(String[] args) {

		BankImpl b = new BankImpl();
		String client1 = "pierre-jean";
		String client2 = "steve";
		b.addClient(client1);
		b.addClient(client2);
		b.Deposit(client1,1000);
		b.Deposit(client2,1000);
		System.out.println(b);
		b.transfer(client1, client2, 100);
		System.out.println(b);
		b.Withdraw(client2, 90);
		System.out.println(b);
		b.Deposit(client2, 80);
		System.out.println(b);

		



	}
}
