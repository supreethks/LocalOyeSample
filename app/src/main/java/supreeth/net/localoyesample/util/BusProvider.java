package supreeth.net.localoyesample.util;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by supreethks on 6/3/15.
 */
public class BusProvider {

    static Bus bus = new Bus(ThreadEnforcer.MAIN);

    public static Bus getBus() {
        return bus;
    }

}
