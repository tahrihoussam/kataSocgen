package socGen.kata;

import java.util.Objects;
import java.util.function.BiConsumer;


public class BiOperation implements Operation {
	private final Account account1;
	private final Account account2;
	private final BiConsumer<Account, Account> opTransaction;
	

	public BiOperation(Account account1, Account account2,  BiConsumer<Account, Account> opTransaction) {
		this.account1 = Objects.requireNonNull(account1);
		this.account2 = Objects.requireNonNull(account2);
		this.opTransaction = Objects.requireNonNull(opTransaction);
	
	}

	public Account getAccount1() {
		return account1;
	}
	public Account getAccount2() {
		return account2;
	}
	public BiConsumer<Account, Account> getOpTransaction() {
		return opTransaction;
	}
	

	
}