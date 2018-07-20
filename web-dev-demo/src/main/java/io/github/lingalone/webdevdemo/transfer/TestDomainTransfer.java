package io.github.lingalone.webdevdemo.transfer;

import io.github.lingalone.webdevdemo.domain.TestDomain;
import io.github.lingalone.webdevdemo.vo.TestDomainVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/7/20
 */

@Mapper
public interface TestDomainTransfer {

    TestDomainTransfer transfer = Mappers.getMapper(TestDomainTransfer.class);

    @Mapping(source = "id", target = "testDomainId")
    TestDomainVO toVO(TestDomain domain);

    @Mapping(source = "testDomainId", target = "id")
    TestDomain toDO(TestDomainVO vo);

}
