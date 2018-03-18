package com.buzz.util.test;

import com.buzz.util.annotation.Inject;
import com.buzz.util.annotation.Service;

@Service
public class TestService {

    @Inject
    private TestBean testBean;
}
