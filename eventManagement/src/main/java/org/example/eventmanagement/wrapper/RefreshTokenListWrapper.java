package org.example.eventmanagement.wrapper;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.eventmanagement.entity.RefreshToken;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "RefreshTokens")
@XmlAccessorType(XmlAccessType.FIELD)
public class RefreshTokenListWrapper {
    private List<RefreshToken> tokens = new ArrayList<>();

    public List<RefreshToken> getTokens() {
        return tokens;
    }

    public void setTokens(List<RefreshToken> tokens) {
        this.tokens = tokens;
    }
}

