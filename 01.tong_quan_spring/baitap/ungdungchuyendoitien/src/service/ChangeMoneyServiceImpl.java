package service;

import org.springframework.stereotype.Service;

@Service
public class ChangeMoneyServiceImpl implements ChangeMoneyService {
    @Override
    public Double changeMoney(double usd) {
        Double resultChange = usd * 23000;
        return resultChange;
    }
}
