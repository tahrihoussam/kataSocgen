package socGen.kata;

import java.util.Objects;
import java.util.function.Consumer;

public class MonoOperation implements Operation {
	private final Account account;
	private final Consumer<Account> opTransaction;

	public MonoOperation(Account right, Consumer<Account> opTransaction) {
		this.account = Objects.requireNonNull(right);
		this.opTransaction = Objects.requireNonNull(opTransaction);
	}

	public Account getAccount() {
		return account;
	}
	public Consumer<Account> getOpTransaction() {
		return opTransaction;
	}
	
}