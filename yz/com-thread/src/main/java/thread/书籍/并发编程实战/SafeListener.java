package thread.书籍.并发编程实战;

/**
 * SafeListener
 * <p/>
 * Using a factory method to prevent the this reference from escaping during construction
 *
 * @author Brian Goetz and Tim Peierls
 */
public class SafeListener {
    private final EventListener listener;

    private SafeListener() {
        listener = new EventListener() {
            public void onEvent(Event e) {
                doSomething(e);
            }
        };
    }

    public static SafeListener newInstance(EventSource source) {
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }

    void doSomething(Event e) {
    }


    interface EventSource {
        void registerListener(EventListener e);
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {
    }
    
    public static void main(String[] args) {
    	SafeListener.newInstance(new SafeListener.EventSource() {
			@Override
			public void registerListener(EventListener e) {
				e.onEvent( new Event(){});
			}
		});
	}
}

