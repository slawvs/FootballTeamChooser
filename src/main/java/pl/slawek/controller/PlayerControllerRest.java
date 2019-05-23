package pl.slawek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slawek.data.PlayerRepository;
import pl.slawek.model.Player;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
public class PlayerControllerRest {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerControllerRest(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // Aggregate root

    @GetMapping
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    //Single item

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable long id) {
        Optional<Player> player = playerRepository.findById(id);
        if(player.isPresent()) {
            return player.get();
        } else {
            return null;
        }
    }
}
