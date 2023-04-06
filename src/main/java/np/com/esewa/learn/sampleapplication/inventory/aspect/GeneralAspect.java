package np.com.esewa.learn.sampleapplication.inventory.aspect;

import lombok.extern.slf4j.Slf4j;
import np.com.esewa.learn.sampleapplication.inventory.model.Product;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;

@Aspect
@Slf4j
@Component
public class GeneralAspect {
    @Pointcut("@annotation(np.com.esewa.learn.sampleapplication.inventory.annotation.EncryptName)")
    public void encryptPointCut() {
    }

//    @Pointcut("@annotation(np.com.esewa.learn.sampleapplication.inventory.annotation.DecryptName)")
//    public void decryptPointCut(){
//    }

    @Before("encryptPointCut()")
    public void before(JoinPoint joinPoint) {
        List<Product> arg = ((List<Product>) joinPoint.getArgs()[0]);
        for(Product product: arg){
            product.setName(Base64.getEncoder().encodeToString(product.getName().getBytes()));
        }
    }
//    @After("decryptPointCut()")
//    public void after(JoinPoint joinPoint) {
//         arg = ((List<Product>) joinPoint.getArgs()[0]);
//        for(Product product: arg){
//            byte[] actualByte = Base64.getDecoder()
//                    .decode(product.getName());
//            String actualString = new String(actualByte);
//            product.setName(actualString);
//        }
//    }

}
