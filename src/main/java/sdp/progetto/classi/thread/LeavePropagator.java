package sdp.progetto.classi.thread;

import io.grpc.stub.StreamObserver;
import sdp.progetto.grpc.ACK;
import sdp.progetto.grpc.LeaveData;
import sdp.progetto.grpc.RingNetworkGrpc;

/** Propagator per un messaggio di abbandono della rete **/
public class LeavePropagator extends Propagator {

    public LeavePropagator(QueueManager manager, LeaveData message) {
        super(manager, message);
    }

    /**
     * Oltre a propagare il messaggio, avvisa il client in caso il destinatario
     * sia in procinto di abbandonare la rete, cambiandone il next a quello successivo
     * **/
    protected void propagate() {
        RingNetworkGrpc.RingNetworkStub stub = RingNetworkGrpc.newStub(manager.getChannel());
        stub.leave((LeaveData) message, new StreamObserver<ACK>() {
            @Override
            public void onNext(ACK ack) {
                if (!ack.getOK()) {
                    manager.setNodeNext(ack.getNextNodeID());
                    manager.newChannelTarget((ack.getNextNodeID()));
                }
            }
            @Override
            public void onError(Throwable throwable) { }
            @Override
            public void onCompleted() { }
        });
    }

}
