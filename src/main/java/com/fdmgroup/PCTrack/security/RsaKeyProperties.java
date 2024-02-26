<<<<<<< HEAD
package com.arceus.SpringTemplate.security;
=======
package com.fdmgroup.PCTrack.security;
>>>>>>> SQLCheck

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rsa")
public record RsaKeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {

}
