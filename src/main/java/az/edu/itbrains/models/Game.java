package az.edu.itbrains.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Game {
    private Long id;
    private int quantity;
    private List<Player> players;
}
