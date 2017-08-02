package socGen.kata;

import java.util.HashMap;
import java.util.function.BiConsumer;


public class LambdaEngineTransactionVisitor<P> {
	
	private final HashMap<Class<? extends Operation>, BiConsumer<?, P>> map = new HashMap<>();

	public <T extends Operation> LambdaEngineTransactionVisitor<P> when(
			Class<T> type,
			BiConsumer<T, P> function) {
		map.put(type, function);
		return this;
	}

	public void visit(Operation op, P param) {
		@SuppressWarnings("unchecked")
		BiConsumer<Operation, P> function = (BiConsumer<Operation, P>) map.get(op.getClass());
		if(function == null){
			throw new IllegalStateException("no function for "+op.getClass());
		}
		 function.accept(op, param);
	}

}
