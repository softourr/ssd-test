# ✨ 프로젝트 개요
가상 SSD(Solid State Drive)를 소프트웨어적으로 구현하여 SSD의 `Read` 및 `Write` 명령어를 처리하고, 결과를 파일로 관리하는 프로그램입니다. 사용자가 입력한 명령어를 통해 LBA(Logical Block Address) 단위로 데이터를 저장하거나 읽어올 수 있습니다.

프로그램은 `MVC` 패턴을 기반으로 설계되었으며, `싱글톤(Singleton)` 패턴을 사용하여 가상 SSD의 일관된 상태를 유지합니다. 또한 `nand.txt` 파일을 이용해 데이터를 관리하며, `0x` 16진수 형식의 데이터를 `char` 형식으로 변환해 파일 크기를 400바이트로 유지합니다.
# 🔖 프로젝트 구조
## 디렉토리 구조
```markdown
ssd
├── controller 
│   └── SsdController.java 
├── model 
│   ├── Ssd.java 
├── repository
│   └── SsdRepository.java 
├── view 
│   └── SsdView.java 
└── main 
    └── Main.java
```
# 🔖 기능 요구 사항
1. **명령어 기능**:
    - `write`: 특정 LBA에 데이터를 기록합니다.
    - `read`: 특정 LBA의 데이터를 읽어옵니다.
    - `fullwrite`: 모든 LBA에 동일한 값을 기록합니다.
    - `fullread`: 모든 LBA의 데이터를 읽어옵니다.
2. **LBA 범위 및 데이터 규칙**:
    - LBA 범위: 0 ~ 99 (총 100칸)
    - 각 LBA의 데이터는 `0x`로 시작하는 8자리 16진수 값이어야 합니다. (예: 0xABCD1234)
3. **파일 관리**:
    - `nand.txt`: SSD의 데이터가 저장되는 파일로, 모든 `write` 명령어 수행 시 값이 기록됩니다.
    - `result.txt`: `read` 명령어 수행 시, 읽은 값이 기록됩니다. 기존의 값은 덮어쓰기로 교체됩니다.
4. **입력 검증**:
    - LBA는 0 ~ 99 사이의 값을 가져야 하며, 유효하지 않은 값 입력 시 에러 메시지를 출력합니다.
    - 값은 8자리 16진수 형식이어야 하며, 형식이 맞지 않으면 "INVALID COMMAND"를 출력합니다.
5. **테스트 프로그램**:
    - `Test Shell` 프로그램은 SSD 프로그램을 실행하여 명령어를 테스트합니다.
    - `testApp1`: 모든 LBA에 동일한 값을 기록하고 읽어 정상 동작 여부를 확인합니다.
    - `testApp2`: 특정 LBA에 여러 번 데이터를 쓰고 덮어쓰기 후, 정상적으로 읽히는지 확인합니다.
# 🔖 프로그램 실행 및 명령어 사용법
프로젝트는 Maven을 이용해 jar 파일로 빌드되었습니다.
## SSD 프로그램 실행
```bash
# 3번 LBA 영역에 값 0x1298CDFF 저장 (write 명령어) 
java -jar ssd.jar W 3 0x1298CDFF 
# 2번 LBA 영역의 데이터 읽기 (read 명령어) 
java -jar ssd.jar R 2
```
## Test Shell 프로그램 실행
```bash
# Test Shell 실행
java -jar testShell.jar
# 3번 LBA에 값 0xAAAABBBB 기록
write 3 0xAAAABBBB
# 3번 LBA 읽기
read 3
# 모든 LBA에 0xABCDFFFF 값을 기록
fullwrite 0xABCDFFFF
# 모든 LBA 값 읽기
fullread
```
