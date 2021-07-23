package sdp.progetto.classi.thread;

import io.grpc.stub.StreamObserver;
import sdp.progetto.grpc.ACK;
import sdp.progetto.grpc.JoinData;
import sdp.progetto.grpc.RingNetworkGrpc;

/** Propagator per un messaggio di inserimento nella rete **/
public class JoinPropagator extends Propagator {

    public JoinPropagator(QueueManager manager, JoinData message) {
        super(manager, message);
    }

    protected void propagate() {
        RingNetworkGrpc.RingNetworkStub stub = RingNetworkGrpc.newStub(manager.getChannel());
        stub.join((JoinData) message, new StreamObserver<ACK>() {
            @Override
            public void onNext(ACK ack) { }
            @Override
            public void onError(Throwable throwable) { }
            @Override
            public void onCompleted() { }
        });
    }

}
