package mtk.eon.net;

import java.util.ArrayList;

public class AnycastDemand extends Demand {

	NetworkNode client;
	boolean isUpstream;
	
	public AnycastDemand(NetworkNode client, boolean isUpstream, int volume, int ttl) {
		super(volume, ttl);
		this.client = client;
		this.isUpstream = isUpstream;
	}

	@Override
	public ArrayList<PartedPath> getCandidatePaths(Network network) {
		ArrayList<PartedPath> paths = new ArrayList<PartedPath>();
		
		if (isUpstream)
			for (NetworkNode replica : network.getReplicas()) for (NetworkPath path : network.getPaths(client , replica)) paths.add(new PartedPath(network, path, path.get(0) == client));
		else
			for (NetworkNode replica : network.getReplicas()) for (NetworkPath path : network.getPaths(replica, client)) paths.add(new PartedPath(network, path, path.get(0) == replica));
		paths.sort(PartedPath.LENGTH_COMPARATOR);
		
		return null;
	}
}