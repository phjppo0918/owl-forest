/* eslint-disable no-template-curly-in-string */
/* eslint-disable jsx-a11y/control-has-associated-label */
/* eslint-disable react/self-closing-comp */
/* eslint-disable no-undef */
import React, { useEffect } from 'react';
import styled, { ThemeProvider } from 'styled-components';
import { palette } from 'styled-tools';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import axios from 'axios';

import imgfile from '../../Image/Loginlogo.png';
import githubbtn from '../../Image/githubbtn.png';
import googlebtn from '../../Image/googlebtn.png';
import kakaobtn from '../../Image/kakaobtn.png';
import naverbtn from '../../Image/naverbtn.png';
import theme from '../../Components/Color';
import kakao from '../../Image/Kakao.png';
import google from '../../Image/Google.png';
import naver from '../../Image/Naver.png';
import { KAKAO_AUTH_URL } from '../../Components/MMList/kakaoAuth';
// 1rem = 대략 16px

const Mainlogo = styled.img`
  width: 10rem;
  height: 10rem;
  display: flex;
  padding: 1.2rem;
`;

const LoginWrap = styled.div`
  position: absolute;
  width: 100%;
  height: 100%;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  display: flex;
  text-align: center;
  margin: 0 auto;
  background-color: ${palette('PsWhite')};
`;

const SocialWrap = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const SocialImg = styled.img`
  width: 3.5rem;
  height: 3.5rem;
  border-radius: 3rem;
  &:hover {
    background-color: blue;
  }
`;
const GoogleSocialImg = styled.img`
  width: 3.5rem;
  height: 3.5rem;
  border-radius: 3rem;
  position: absolute;
`;

const DoubleSocialImg = styled.img`
  width: 3.5rem;
  height: 3.5rem;
  border-radius: 3rem;
  opacity: 0;
  &:hover {
    width: 20rem;
    transform: scale(1.2);
    opacity: 1;
  }
`;
const KakaoSocialImg = styled.img`
  width: 3.5rem;
  height: 3.5rem;
  border-radius: 3rem;
  position: absolute;
  margin-top: 12rem;
`;

const NaverSocialImg = styled.img`
  width: 3.5rem;
  height: 3.5rem;
  border-radius: 3rem;
  position: absolute;
  margin-top: 17.7rem;
`;
function Login() {
  useEffect(() => {
    axios({
      url: '/api/auth/login/google',
      method: 'post',
    }).then((response) => {
      setText(response.data);
    });
  }, []);

  // 선언
  return (
    <div>
      <ThemeProvider theme={theme}>
        <LoginWrap>
          <Mainlogo src={imgfile} />
          <SocialWrap>
            <GoogleSocialImg style={{ margin: '1rem' }} src={googlebtn} />
            <a href='http://localhost:8080/api/auth/login/google'>
              <DoubleSocialImg style={{ margin: '1rem' }} src={google} />
            </a>
            <SocialImg style={{ margin: '1rem' }} src={githubbtn} />
            <KakaoSocialImg style={{}} src={kakaobtn} />
            <a href={KAKAO_AUTH_URL}>
              <DoubleSocialImg style={{ margin: '1rem' }} src={kakao} />
            </a>
            <NaverSocialImg style={{}} src={naverbtn} />
            <DoubleSocialImg style={{ margin: '1rem' }} src={naver} />
          </SocialWrap>
        </LoginWrap>
      </ThemeProvider>
    </div>
  );
}
export default Login;
