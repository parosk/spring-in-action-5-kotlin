package hk.com.martiansapp.tacocloudkotlin.web

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Component
@ConfigurationProperties(prefix = "taco.orders")
class OrderProps {

    @field:Max(value = 25, message = "must be between 5 and 25")
    @field:Min(value = 5, message = "must be between 5 and 25")
    var pageSize = 20
}