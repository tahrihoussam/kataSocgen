package socGen.kata;

import java.util.Objects;

public class AccountImpl implements Account {
	private int account;

	public AccountImpl(int credit) {

		this.account = Objects.requireNonNull(credit);
	}

	public int getCredit() {

		return account;
	}

	public void Withdraw(int sum) {
		if(account<sum)
			throw new IllegalStateException();
		account-=sum;
	}

	public void Deposit(int sum) {
		account+=sum;
	}

	@Override
	public String toString() {

		return "" + account;
	}

}
