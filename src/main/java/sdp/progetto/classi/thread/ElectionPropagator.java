package sdp.progetto.classi.thread;

import io.grpc.stub.StreamObserver;
import sdp.progetto.grpc.ACK;
import sdp.progetto.grpc.ElectionData;
import sdp.progetto.grpc.RingNetworkGrpc;

/** Propagator per un messaggio di elezione **/
public class ElectionPropagator extends Propagator {

    public ElectionPropagator(QueueManager manager, ElectionData message) {
        super(manager, message);
    }

    protected void propagate() {
        RingNetworkGrpc.RingNetworkStub stub = RingNetworkGrpc.newStub(manager.getChannel());
        stub.election((ElectionData) message, new StreamObserver<ACK>() {
            @Override
            public void onNext(ACK ack) { }
            @Override
            public void onError(Throwable throwable) { }
            @Override
            public void onCompleted() { }
        });
    }

}
