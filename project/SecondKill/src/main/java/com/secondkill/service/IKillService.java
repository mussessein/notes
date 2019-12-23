package com.secondkill.service;

/**
 * 秒杀service
 * 1.
 */
public interface IKillService {

    Boolean killItemV1(Integer killId,Integer userId) throws Exception;

    Boolean killItemV2(Integer killId, Integer userId) throws Exception;

    Boolean killItemV3(Integer killId, Integer userId) throws Exception;

    Boolean killItemV4(Integer killId, Integer userId) throws Exception;

}