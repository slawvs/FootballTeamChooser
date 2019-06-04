package pl.slawek.component;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;
import pl.slawek.controller.PlayerControllerRest;
import pl.slawek.model.Player;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class PlayerResourceAssembler implements ResourceAssembler<Player, Resource<Player>> {


    @Override
    public Resource<Player> toResource(Player player) {

        return new Resource<>(player,
                linkTo(methodOn(PlayerControllerRest.class).getPlayer(player.getId())).withSelfRel(),
                linkTo(methodOn(PlayerControllerRest.class).getPlayers()).withRel("players"));
    }
}
