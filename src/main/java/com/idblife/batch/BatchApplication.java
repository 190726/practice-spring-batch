package com.idblife.batch;

import com.github.javafaker.Faker;
import com.idblife.batch.domain.user.User;
import com.idblife.batch.domain.user.UserRepository;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Locale;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@EnableBatchProcessing // 배치 기능 활성화 어노테이션
@SpringBootApplication
public class BatchApplication {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

	/*
	@Bean
	public ApplicationRunner init(){
		return args -> {
			LongStream.range(1, 100).forEach(
					i->{
						Faker user = new Faker(Locale.KOREAN);
						userRepository.save(new User(i, user.name().fullName(), user.internet().emailAddress()));
					}
			);
		};
	}
	*/
}