package All.exception;

import All.exception.cart.CartException;
import All.exception.category.CategoryException;
import All.exception.product.ProductException;
import All.exception.user.AuthenticationFailException;
import All.exception.user.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = CategoryException.class)
    public final ResponseEntity<?> handleCategoryException(CategoryException categoryException) {
        return new ResponseEntity<>(categoryException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions() {
        return ResponseEntity.badRequest().body("Bad credentials");
    }

    @ExceptionHandler(value = ProductException.class)
    public final ResponseEntity<?> handleProductException(ProductException productException) {
        return new ResponseEntity<>(productException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AuthenticationFailException.class)
    public final ResponseEntity<String> handleAuthenticationFailException(AuthenticationFailException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserException.class)
    public final ResponseEntity<String> handleUserException(UserException userException) {
        return new ResponseEntity<>(userException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CartException.class)
    public final ResponseEntity<String> handleCartException(CartException cartException) {
        return new ResponseEntity<>(cartException.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
