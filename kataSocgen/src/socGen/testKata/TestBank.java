package socGen.testKata;

import static org.junit.Assert.*;

import org.junit.Test;

import socGen.kata.Account;
import socGen.kata.AccountImpl;
import socGen.kata.BankImpl;

public class TestBank {

	@Test(expected = NullPointerException.class)
	public void test_Bank_addClient_Null_Client_Name_With_blank_Account() {
		BankImpl b = new BankImpl();

		b.addClient(null);
	}

	@Test(expected = NullPointerException.class)
	public void test_Bank_addClient_Null_Client_Name_With_Account() {
		BankImpl b = new BankImpl();

		b.addClient(null, new AccountImpl(0));
	}

	@Test(expected = NullPointerException.class)
	public void test_Bank_AddClient_Null_Client_Account() {
		BankImpl b = new BankImpl();

		b.addClient("pierre-jean", null);
	}

	@Test(expected = NullPointerException.class)
	public void test_Bank_RemoveClient_Null_Client_Name() {
		BankImpl b = new BankImpl();

		b.removeClient(null);
	}
	@Test
	public void test_Bank_AddClient() {
		BankImpl b = new BankImpl();

		b.addClient("pierre-jean");
		
		assertTrue(b.ClientExist("pierre-jean"));
	}
	

	@Test
	public void test_Bank_RemoveClient_() {
		BankImpl b = new BankImpl();

		b.addClient("pierre-jean");
		b.removeClient("pierre-jean");
		assertFalse(b.ClientExist("pierre-jean"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_Bank_Withdraw_Minus_Amount() {
		BankImpl b = new BankImpl();
		Account jpAccount = new AccountImpl(100);
		b.addClient("pierre-jean",jpAccount);
		b.Withdraw("pierre-jean", -10);
	}
	
	@Test(expected = IllegalStateException.class)
	public void test_Bank_Withdraw_Overdraft() {
		BankImpl b = new BankImpl();
		Account jpAccount = new AccountImpl(100);
		b.addClient("pierre-jean",jpAccount);
		b.Withdraw("pierre-jean", 1000);
		assertEquals(jpAccount.getCredit(),90);
	}
	
	@Test
	public void test_Bank_Withdraw() {
		BankImpl b = new BankImpl();
		Account jpAccount = new AccountImpl(100);
		b.addClient("pierre-jean",jpAccount);
		b.Withdraw("pierre-jean", 10);
		assertEquals(jpAccount.getCredit(),90);
	}
	@Test(expected = IllegalArgumentException.class)
	public void test_Bank_Withdraw_Minus_Deposit() {
		BankImpl b = new BankImpl();
		Account jpAccount = new AccountImpl(100);
		b.addClient("pierre-jean",jpAccount);
		b.Deposit("pierre-jean", -10);
	}
	
	@Test
	public void test_Bank_Deposit() {
		BankImpl b = new BankImpl();
		Account jpAccount = new AccountImpl(90);
		b.addClient("pierre-jean",jpAccount);
		b.Deposit("pierre-jean", 10);
		assertEquals(jpAccount.getCredit(),100);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_Bank_Withdraw_Minus_transfer() {
		BankImpl b = new BankImpl();
		String client1 = "pierre-jean";
		String client2 = "steve";
		b.addClient(client1);
		b.addClient(client2);
		b.Deposit(client1,1000);
		b.Deposit(client2,1000);
		b.transfer(client1, client2, -100);
	}
	
	@Test
	public void test_Bank_transfer() {
		BankImpl b = new BankImpl();
		String client1 = "pierre-jean";
		String client2 = "steve";
		b.addClient(client1);
		b.addClient(client2);
		b.Deposit(client1,1000);
		b.Deposit(client2,1000);
		b.transfer(client1, client2, 100);
		assertEquals(b.getAccount(client1).getCredit(),900);
		assertEquals(b.getAccount(client2).getCredit(),1100);
	}
	
	

}
