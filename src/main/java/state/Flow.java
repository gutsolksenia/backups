package state;

import model.Channel;
import model.SubNetwork;

import java.util.*;

public class Flow {
    private final Map<Channel, Integer> flows = new HashMap<>();
    private final Map<SubNetwork, Channel> graph = new HashMap<>();

    public Flow(List<Channel> channels) {
        channels.forEach(channel -> {
            Channel reversed = new Channel(
                    channel.getTo(),
                    channel.getFrom(),
                    -channel.getSpeed()
            );
            flows.put(channel, 0);
            flows.put(reversed, 0);
            graph.put(channel.getFrom(), channel);
            graph.put(reversed.getFrom(), reversed);
        });
    }

    public int maxFlow(SubNetwork subNetwork) {
        return findPath(Collections.singletonList(subNetwork))
                .stream()
                .mapToInt(c -> c.getSpeed())
                .min()
                .orElse(0);
    }

    private List<Channel> findPath(List<SubNetwork> subNetworks) {
        return new ArrayList<>(); //TODO
    }


}