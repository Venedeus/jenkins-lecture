package tdd;

import model.ServantService;

import java.util.Optional;

public class Client {
    private String name;
    private ServantService servant;

    public Client(String name, ServantService servant) throws IllegalArgumentException{
        if(name == null) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.servant = servant;
    }

    public String getName() {
        return name;
    }

    public Optional<Integer> orderDish(String title) {
        return servant.orderCooking(title);
    }
}
