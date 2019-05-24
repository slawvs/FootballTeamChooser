package pl.slawek.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;
import pl.slawek.component.PlayerResourceAssembler;
import pl.slawek.data.PlayerRepository;
import pl.slawek.model.Player;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/players")
public class PlayerControllerRest {

    private PlayerRepository playerRepository;

    private PlayerResourceAssembler playerResourceAssembler;

    @Autowired
    public PlayerControllerRest(PlayerRepository playerRepository, PlayerResourceAssembler playerResourceAssembler) {
        this.playerRepository = playerRepository;
        this.playerResourceAssembler = playerResourceAssembler;
    }

    // Aggregate root

    @GetMapping
    public Resources<Resource<Player>> getPlayers() {

        List<Resource<Player>> players = playerRepository.findAll().stream()
                .map(playerResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(players,
                linkTo(methodOn(PlayerControllerRest.class).getPlayers()).withSelfRel());
    }

    @PostMapping
    public Player newPlayer(@RequestBody Player newPlayer) {

        return playerRepository.save(newPlayer);
    }

    //Single item

    @GetMapping("/{id}")
    public Resource<Player> getPlayer(@PathVariable long id) {

        Player player = playerRepository.findById(id)
                .orElse(null);

        return playerResourceAssembler.toResource(player);
    }

    @PutMapping("/{id}")
    public Player updatePlayer(@RequestBody Player updatedPlayer, @PathVariable Long id) {

        return playerRepository.findById(id)
                .map(player -> {
                    player.setNickName(updatedPlayer.getNickName());
                    player.setSkillIndex(updatedPlayer.getSkillIndex());
                    return playerRepository.save(player);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {

        playerRepository.deleteById(id);
    }
}
