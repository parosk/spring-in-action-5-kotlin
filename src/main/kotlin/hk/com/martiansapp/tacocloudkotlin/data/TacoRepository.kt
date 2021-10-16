package hk.com.martiansapp.tacocloudkotlin.data

import hk.com.martiansapp.tacocloudkotlin.Taco
import org.springframework.data.repository.CrudRepository

//When generating the repository implementation,
// Spring Data examines any methods in the repository interface,
// parses the method name, and attempts to understand
// the method’s purpose in the context of the persisted object
interface TacoRepository : CrudRepository<Taco, Long>