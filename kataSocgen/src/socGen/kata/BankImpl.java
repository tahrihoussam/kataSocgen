package socGen.kata;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BankImpl implements Bank {

	private final ConcurrentHashMap<String, Account> clients;
	private final LambdaEngineTransactionVisitor<Void> engineTransactionVisitor;

	public BankImpl() {
		clients = new ConcurrentHashMap<String, Account>();
		this.engineTransactionVisitor = new LambdaEngineTransactionVisitor<Void>().
				when(MonoOperation.class, (monoOperation, unused) -> {
					monoOperation.getOpTransaction().accept(monoOperation.getAccount());
				}).
				when(BiOperation.class, (biOperation, unused) -> {
			biOperation.getOpTransaction().accept(biOperation.getAccount1(), biOperation.getAccount2());
		});

	}

	public void addClient(String name, Account account) {
		clients.put(Objects.requireNonNull(name), Objects.requireNonNull(account));
	}

	public void addClient(String name) {
		
		addClient(name, new AccountImpl(0));
	}

	public void removeClient(String name) {
		clients.remove(Objects.requireNonNull(name));
	}

	public Account getAccount(String name) {
		return clients.get(Objects.requireNonNull(name));
	}

	public boolean ClientExist(String name){
		return clients.containsKey(name);
	}
	
	public void transfer(String account1, String account2, int amount) {
		if(amount<0)
			throw new IllegalArgumentException();
		Operation op = new BiOperation(getAccount(account1), getAccount(account2),(accountLeft, accountRight) -> {

			accountLeft.Withdraw(amount);
			accountRight.Deposit(amount);
		});
		engineTransactionVisitor.visit(op, null);
	}

	public void Withdraw(String right, int amount) {
		if(amount<0)
			throw new IllegalArgumentException();
		Operation op = new MonoOperation(getAccount(right), (accountRight) -> accountRight.Withdraw(amount));
		engineTransactionVisitor.visit(op, null);
	}

	public void Deposit(String right, int amount) {
		if(amount<0)
			throw new IllegalArgumentException();
		Operation op = new MonoOperation(getAccount(right), (accountRight) -> accountRight.Deposit(amount));
		engineTransactionVisitor.visit(op, null);
	}

	@Override
	public String toString() {

		return clients.entrySet().stream()
				.map(entry -> entry.getKey() + " - " + entry.getValue())
				.collect(Collectors.joining(", "));
	}

}
