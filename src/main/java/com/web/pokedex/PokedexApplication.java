package com.web.pokedex;

import com.web.pokedex.model.Pokemon;
import com.web.pokedex.repository.PokemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApplication.class, args); }

		@Bean
	CommandLineRunner init (ReactiveMongoOperations operations, PokemonRepository repository){
		return args -> {
			Flux<Pokemon> pokemonFlux = Flux.just(
					new Pokemon(null, "Bubassaur", "Aquatico", "Jato de água", 9.0),
					new Pokemon(null, "Cartepie", "inseto", "poeira do excuto", 5.6),
					new Pokemon(null, "Pikachu", "Eletrico", "Shock do trovão", 11.0))
					.flatMap(repository::save);
			pokemonFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);

		};
		}


	}
