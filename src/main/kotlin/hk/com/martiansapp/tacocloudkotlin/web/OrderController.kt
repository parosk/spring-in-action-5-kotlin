package hk.com.martiansapp.tacocloudkotlin.web

import hk.com.martiansapp.tacocloudkotlin.Order
import hk.com.martiansapp.tacocloudkotlin.User
import hk.com.martiansapp.tacocloudkotlin.data.OrderRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import javax.validation.Valid


@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@ConfigurationProperties(prefix="taco.orders") //define variable that can be set by env var
class OrderController(@Autowired val orderRepo: OrderRepository,@Autowired val props: OrderProps) {


    private val logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/current")
    open fun orderForm(@AuthenticationPrincipal user: User,
                       @ModelAttribute order: Order): String? {
        if (order.deliveryName == null) {
            order.deliveryName = user.username
        }
        if (order.street == null) {
            order.street = user.street
        }
        if (order.city == null){
            order.city = user.city
        }
        if (order.state == null) {
            order.state = user.state
        }
        if (order.zip == null) {
            order.zip = user.zip
        }
        return "orderForm"
    }

    @GetMapping
    fun ordersForUser(
        @AuthenticationPrincipal user: User?, model: Model
    ): String? {
        val pageable: Pageable = PageRequest.of(0, props.pageSize)
        model.addAttribute(
            "orders",
            orderRepo.findByUserOrderByPlacedAtDesc(user, pageable)
        )
        return "orderList"
    }

    @PostMapping
    fun processOrder(@Valid order: Order, errors: Errors, sessionStatus: SessionStatus, @AuthenticationPrincipal  user: User): String? {
        if (errors.hasErrors()) {
            return "orderForm"
        }


        /**
         * use the below method anywhere in the application, not only in a controller’s handler methods.
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        order.user = authentication.principal as User
        */
        order.user = user
        orderRepo.save(order)
        sessionStatus.setComplete()
        logger.info("Order submitted: $order")
        return "redirect:/"
    }
}