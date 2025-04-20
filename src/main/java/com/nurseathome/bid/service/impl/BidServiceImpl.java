package com.nurseathome.bid.service.impl;

import com.nurseathome.bid.client.NotificationClient;
import com.nurseathome.bid.mapper.BidMapper;
import com.nurseathome.bid.model.dto.BidDto;
import com.nurseathome.bid.model.entity.Bid;
import com.nurseathome.bid.model.entity.PatientAddress;
import com.nurseathome.bid.model.entity.Procedure;
import com.nurseathome.bid.model.params.BidParams;
import com.nurseathome.bid.repository.BidRepository;
import com.nurseathome.bid.repository.PatientRepository;
import com.nurseathome.bid.repository.ProcedureRepository;
import com.nurseathome.bid.repository.address.PatientAddressRepository;
import com.nurseathome.bid.service.AddressService;
import com.nurseathome.bid.service.BidService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.nurseathome.bid.utils.JwtUtils.getSsoUserIdFromToken;
import static java.util.Objects.isNull;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class BidServiceImpl implements BidService {

    BidMapper bidMapper;
    AddressService addressService;

    NotificationClient notificationClient;

    BidRepository bidRepository;
    PatientAddressRepository patientAddressRepository;
    PatientRepository patientRepository;
    ProcedureRepository procedureRepository;

    @Override
    @Transactional
    public BidDto create(BidParams params) {
        val patient = patientRepository.findBySsoUserId(getSsoUserIdFromToken())
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Не удалось найти пациента по токену"));

        val patientId = patient.getId();
        val newBid = bidMapper.toBid(params).setPatientId(patientId);

        val address = !isNull(params.getAddress())
                ? addressService.checkAddressAndReturn(params.getAddress())
                : patientAddressRepository.findByPatientIdAndIsPrimaryTrue(patientId)
                        .map(PatientAddress::getAddress)
                        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                                "У вас не указан основной адрес. Обновите адрес"));

        if (!patientAddressRepository.existsByPatientIdAndAddressId(patientId, address.getId())) {
            patientAddressRepository.save(new PatientAddress(patient, address, false));
        }

        newBid.setAddress(address);

        List<Procedure> procedures = procedureRepository.findAllById(params.getProcedureIds());
        if (procedures.size() < params.getProcedureIds().size()) {
            throw new ResponseStatusException(BAD_REQUEST, "Укажите корректные ID услуг");
        }

        if (params.getScheduledTime() == null) {
            return createUrgentBid(newBid);
        } else {
            return createScheduledBid(newBid);
        }

        // а если заявка на конкретное время, то искать

        //        if (newBid.getNurseId() == null) {
        //            //TODO отправить уведомления всем медсестрам в округе, которые выполняют все указанные услуги
        //        } else {
        //            //TODO отправить уведомление конкретной медсестре
        //        }
        //        return null;
    }

    private BidDto createUrgentBid(Bid bid) {
        //TODO если заявка "как можно быстрее" то если нет ни одного ответа перевести в статус Rejected и отправить юзеру уведомление об этом, чтоб он если что повторил заявку
        if (bid.getNurseId() == null) {
            //TODO отправить уведомления всем медсестрам в округе отсортировав по дальности
            // значит надо
            // 1. Получить геопозицию пациента по его адресу (если в адресе нет, то из сервиса яндекса, и сохранить в адрес)
            // 2. найти всех медсестер в радиусе 10 км
            // 3. отфильтровать их так, чтобы:
            // - местоположение пациента входило в область поиска заказов медсестры
            // - сестра была доступна
            // - не была в ЧС у пациента,
            // - он не был в ЧС у медсестры
            // - медсестра бы исполняла указанную процедуру
            // 4. отсортировать их по отдаленности и отправлять каждой из них уведомление по очереди, ожидая 15 секунд на ответ.
            // 5. если никто не ответил, то поставить статус rejected и отправить уведомление пациенту
        } else {
            //TODO отправить уведомления сестрам в округе, но первой поставить указанную медсестру
        }
        return null;
    }

    private BidDto createScheduledBid(Bid bid) {
        //TODO если заявка ко времени то поставить ей статус SCHEDULED и создать шедулер, который в указанное время -10 минут вызовет логику отправки,
        // если без ответа перевести в статус Rejected и отправить юзеру уведомление об этом, чтоб он если что повторил заявку
        if (bid.getNurseId() == null) {
            //TODO отправить уведомления всем медсестрам в округе отсортировав по дальности
            // значит надо
            // 1. Получить геопозицию пациента по его адресу (если в адресе нет, то из сервиса яндекса, и сохранить в адрес)
            // 2. найти всех медсестер в радиусе 10 км
            // 3. отфильтровать их так, чтобы:
            // - местоположение пациента входило в область поиска заказов медсестры
            // - сестра была доступна
            // - не была в ЧС у пациента,
            // - он не был в ЧС у медсестры
            // - медсестра бы исполняла указанную процедуру
            // 4. отсортировать их по отдаленности и отправлять каждой из них уведомление по очереди, ожидая 15 секунд на ответ.
            // 5. если никто не ответил, то поставить статус rejected и отправить уведомление пациенту
        } else {
            //TODO отправить уведомления сестрам в округе, но первой поставить указанную медсестру
        }
        return null;
    }

    private void sendNotification() {

    }
}
