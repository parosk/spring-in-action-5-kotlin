package hk.com.martiansapp.tacocloudkotlin.web

import hk.com.martiansapp.tacocloudkotlin.Order
import hk.com.martiansapp.tacocloudkotlin.data.OrderRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import javax.validation.Valid


@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
class OrderController(@Autowired val orderRepo: OrderRepository) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/current")
    fun orderForm(model: Model): String {
        return "orderForm"
    }

    @PostMapping
    fun processOrder(@Valid order: Order, errors: Errors, sessionStatus: SessionStatus): String? {
        if (errors.hasErrors()) {
            return "orderForm"
        }
        orderRepo.save(order)
        sessionStatus.setComplete()
        logger.info("Order submitted: $order")
        return "redirect:/"
    }
}