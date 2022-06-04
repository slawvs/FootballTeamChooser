package pl.slawek.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.slawek.component.PlayerResourceAssembler;
import pl.slawek.data.PlayerRepository;
import pl.slawek.exception.PlayerNotFoundException;
import pl.slawek.model.Player;
import pl.slawek.model.Team;
import pl.slawek.service.PlayerService;
import pl.slawek.service.TeamsCalculating;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/players")
public class PlayerControllerRest {

    private PlayerRepository playerRepository;
    private PlayerResourceAssembler playerResourceAssembler;

    private TeamsCalculating teamscalculating;

    @Autowired
    public PlayerControllerRest(PlayerRepository playerRepository, PlayerResourceAssembler playerResourceAssembler, TeamsCalculating teamscalculating) {
        this.playerRepository = playerRepository;
        this.playerResourceAssembler = playerResourceAssembler;
        this.teamscalculating = teamscalculating;
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
    public ResponseEntity<?> newPlayer(@RequestBody Player newPlayer) throws URISyntaxException {

        Resource<Player> resource = playerResourceAssembler.toResource(playerRepository.save(newPlayer));

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    //Single item

    @GetMapping("/{id}")
    public Resource<Player> getPlayer(@PathVariable long id) {

        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));

        return playerResourceAssembler.toResource(player);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlayer(@RequestBody Player newPlayer, @PathVariable Long id) throws URISyntaxException {

        Player updatedPlayer = playerRepository.findById(id)
                .map(player -> {
                    player.setNickName(newPlayer.getNickName());
                    player.setSkillIndex(newPlayer.getSkillIndex());
                    return playerRepository.save(player);
                })
                .orElseThrow(() -> new PlayerNotFoundException(id));

        Resource<Player> resource = playerResourceAssembler.toResource(updatedPlayer);

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable Long id) {

        Player player = playerRepository.findById(id)
                .orElseThrow(()-> new PlayerNotFoundException(id));

        playerRepository.delete(player);

        return ResponseEntity.noContent().build();
    }

    //testing posibility to calculate teams with REST
    @PostMapping("/calculate/{number}")
    public TeamsRest getTeams(@RequestBody List<Player> players,@PathVariable Integer number) {

        teamscalculating.setAllPlayers(players);
        teamscalculating.setNumberOfplayersInTeam(number);
        teamscalculating.CalculateSquads();
/*        List <Player> blackTeam = teamscalculating.getBlackTeam();
        List <Player> whiteTeam = teamscalculating.getWhiteTeam();*/

        TeamsRest teamsRest = new TeamsRest(teamscalculating.getBlackTeam(),teamscalculating.getWhiteTeam());
/*        List<Resource<Player>> playersResource = players.stream()
                .map(playerResourceAssembler::toResource)
                .collect(Collectors.toList());*/
/*        return new Resources<>(playersResource,
                linkTo(methodOn(PlayerControllerRest.class).getPlayers()).withSelfRel());*/
        return teamsRest;
    }
}
