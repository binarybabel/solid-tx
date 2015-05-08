package solidtxsample;

import org.binbab.solidtx.TxException;
import org.binbab.solidtx.TxFn;
import org.binbab.solidtx.TxStack;

public class Main {

    public static void main(String[] args) {
        TxStack.debug = true;
        Stack s = new Stack();

        s.callObject(Person.class, 1, new TxFn() {
            @Override
            public void run(TxStack stack, Object obj, TxException e) {
                Person p = (Person) obj;
                TxStack.debug(p.firstName, null);
                TxStack.debug(p.lastName, null);
                TxStack.debug(p.hometown.name, null);
            }
        });

        s.callObject(Person.class, 2, new TxFn() {
            @Override
            public void run(TxStack stack, Object obj, TxException e) {
                Person p = (Person) obj;
                TxStack.debug(p.firstName, null);
                TxStack.debug(p.lastName, null);
                for (String c : p.favorite_colors) {
                    TxStack.debug(c, null);
                }
                for (Thing t : p.favorite_things) {
                    TxStack.debug(t.name, null);
                }
            }
        });

        s.callObject(Person.class, 99, new TxFn() {
            @Override
            public void run(TxStack stack, Object obj, TxException e) {
                if (obj == null) {
                    TxStack.debug(e.getMessage(), null);
                }
            }
        });

        s.callObject(Thing.class, "8639d2c5-94ff-4350-8239-358839a5c0fa", new TxFn() {
            @Override
            public void run(TxStack stack, Object obj, TxException e) {
                Thing t = (Thing) obj;
                TxStack.debug(t.name, null);
            }
        });

        try {
            s.sync();
        } catch (TxException e) {
            e.printStackTrace();
        }
        s.run();

    }

}
