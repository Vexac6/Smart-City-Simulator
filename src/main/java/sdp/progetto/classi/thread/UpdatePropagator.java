package sdp.progetto.classi.thread;

import io.grpc.stub.StreamObserver;
import sdp.progetto.grpc.ACK;
import sdp.progetto.grpc.RingNetworkGrpc;
import sdp.progetto.grpc.UpdateData;

/** Propagator per un messaggio di update dei nodi **/
public class UpdatePropagator extends Propagator {

    public UpdatePropagator(QueueManager manager, UpdateData message) {
        super(manager, message);
    }

    protected void propagate() {
        RingNetworkGrpc.RingNetworkStub stub = RingNetworkGrpc.newStub(manager.getChannel());
        stub.update((UpdateData) message, new StreamObserver<ACK>() {
            @Override
            public void onNext(ACK ack) { }
            @Override
            public void onError(Throwable throwable) { }
            @Override
            public void onCompleted() { }
        });
    }

}
