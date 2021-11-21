package hk.com.martiansapp.tacocloudkotlin

import hk.com.martiansapp.tacocloudkotlin.data.IngredientRepository
import hk.com.martiansapp.tacocloudkotlin.data.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.password.PasswordEncoder

@Profile("!prod")
@Configuration
class DevelopmentConfig {
  @Bean
  fun dataLoader(
    repo: IngredientRepository,
    userRepo: UserRepository, encoder: PasswordEncoder
  ): CommandLineRunner { // user repo for ease of testing with a built-in user
    return CommandLineRunner {
      repo.save(Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP))
      repo.save(Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP))
      repo.save(Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN))
      repo.save(Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN))
      repo.save(Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES))
      repo.save(Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES))
      repo.save(Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE))
      repo.save(Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE))
      repo.save(Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE))
      repo.save(Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE))
      userRepo.save(
        User(
          "paros", encoder.encode("password"),
          "Craig Walls", "123 North Street", "Cross Roads", "TX",
          "76227", "123-123-1234"
        )
      )
    }
  }
}