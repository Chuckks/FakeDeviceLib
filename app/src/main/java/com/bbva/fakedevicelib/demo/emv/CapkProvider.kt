package com.bbva.fakedevicelib.demo.emv

import com.bbva.devicelib.emv.configData.CapkData

object CapkProvider {

    val capksTest = listOf(
            CapkData().apply {
                rid = "A000000004"
                index = "00"
                hashInd = "01"
                modul =
                    "9E15214212F6308ACA78B80BD986AC287516846C8D548A9ED0A42E7D997C902C3E122D1B9DC30995F4E25C75DD7EE0A0CE293B8CC02B977278EF256D761194924764942FE714FA02E4D57F282BA3B2B62C9E38EF6517823F2CA831BDDF6D363D"
                exponent = "03"
                expDate = "091231"
                checkSum = "8BB99ADDF7B560110955014505FB6B5F8308CE27"
            },

            CapkData().apply {
                rid = "A000000004"
                index = "01"
                hashInd = "01"
                modul =
                    "D2010716C9FB5264D8C91A14F4F32F8981EE954F20087ED77CDC5868431728D3637C632CCF2718A4F5D92EA8AB166AB992D2DE24E9FBDC7CAB9729401E91C502D72B39F6866F5C098B1243B132AFEE65F5036E168323116338F8040834B98725"
                exponent = "03"
                expDate = "091231"
                checkSum = "EA950DD4234FEB7C900C0BE817F64DE66EEEF7C4"
            },

            CapkData().apply {
                rid = "A000000004"
                index = "02"
                hashInd = "01"
                modul =
                    "CF4264E1702D34CA897D1F9B66C5D63691EACC612C8F147116BB22D0C463495BD5BA70FB153848895220B8ADEEC3E7BAB31EA22C1DC9972FA027D54265BEBF0AE3A23A8A09187F21C856607B98BDA6FC908116816C502B3E58A145254EEFEE2A3335110224028B67809DCB8058E24895"
                exponent = "03"
                expDate = "091231"
                checkSum = "AF1CC1FD1C1BC9BCA07E78DA6CBA2163F169CBB7"
            },

            CapkData().apply {
                rid = "A000000004"
                index = "03"
                hashInd = "01"
                modul =
                    "C2490747FE17EB0584C88D47B1602704150ADC88C5B998BD59CE043EDEBF0FFEE3093AC7956AD3B6AD4554C6DE19A178D6DA295BE15D5220645E3C8131666FA4BE5B84FE131EA44B039307638B9E74A8C42564F892A64DF1CB15712B736E3374F1BBB6819371602D8970E97B900793C7C2A89A4A1649A59BE680574DD0B60145"
                exponent = "03"
                expDate = "091231"
                checkSum = "5ADDF21D09278661141179CBEFF272EA384B13BB"
            },

            CapkData().apply {
                rid = "A000000004"
                index = "04"
                hashInd = "01"
                modul =
                    "A6DA428387A502D7DDFB7A74D3F412BE762627197B25435B7A81716A700157DDD06F7CC99D6CA28C2470527E2C03616B9C59217357C2674F583B3BA5C7DCF2838692D023E3562420B4615C439CA97C44DC9A249CFCE7B3BFB22F68228C3AF13329AA4A613CF8DD853502373D62E49AB256D2BC17120E54AEDCED6D96A4287ACC5C04677D4A5A320DB8BEE2F775E5FEC5"
                exponent = "03"
                expDate = "171231"
                checkSum = "381A035DA58B482EE2AF75F4C3F2CA469BA4AA6C"
            },

            CapkData().apply {
                rid = "A000000004"
                index = "05"
                hashInd = "01"
                modul =
                    "B8048ABC30C90D976336543E3FD7091C8FE4800DF820ED55E7E94813ED00555B573FECA3D84AF6131A651D66CFF4284FB13B635EDD0EE40176D8BF04B7FD1C7BACF9AC7327DFAA8AA72D10DB3B8E70B2DDD811CB4196525EA386ACC33C0D9D4575916469C4E4F53E8E1C912CC618CB22DDE7C3568E90022E6BBA770202E4522A2DD623D180E215BD1D1507FE3DC90CA310D27B3EFCCD8F83DE3052CAD1E48938C68D095AAC91B5F37E28BB49EC7ED597"
                exponent = "03"
                expDate = "241231"
                checkSum = "EBFA0D5D06D8CE702DA3EAE890701D45E274C845"
            },

            CapkData().apply {
                rid = "A000000004"
                index = "06"
                hashInd = "01"
                modul =
                    "CB26FC830B43785B2BCE37C81ED334622F9622F4C89AAE641046B2353433883F307FB7C974162DA72F7A4EC75D9D657336865B8D3023D3D645667625C9A07A6B7A137CF0C64198AE38FC238006FB2603F41F4F3BB9DA1347270F2F5D8C606E420958C5F7D50A71DE30142F70DE468889B5E3A08695B938A50FC980393A9CBCE44AD2D64F630BB33AD3F5F5FD495D31F37818C1D94071342E07F1BEC2194F6035BA5DED3936500EB82DFDA6E8AFB655B1EF3D0D7EBF86B66DD9F29F6B1D324FE8B26CE38AB2013DD13F611E7A594D675C4432350EA244CC34F3873CBA06592987A1D7E852ADC22EF5A2EE28132031E48F74037E3B34AB747F"
                exponent = "03"
                expDate = "281231"
                checkSum = "F910A1504D5FFB793D94F3B500765E1ABCAD72D9"
            },

            CapkData().apply {
                rid = "A000000003"
                index = "95"
                hashInd = "01"
                modul =
                    "BE9E1FA5E9A803852999C4AB432DB28600DCD9DAB76DFAAA47355A0FE37B1508AC6BF38860D3C6C2E5B12A3CAAF2A7005A7241EBAA7771112C74CF9A0634652FBCA0E5980C54A64761EA101A114E0F0B5572ADD57D010B7C9C887E104CA4EE1272DA66D997B9A90B5A6D624AB6C57E73C8F919000EB5F684898EF8C3DBEFB330C62660BED88EA78E909AFF05F6DA627B"
                exponent = "03"
                expDate = "202001"
                checkSum = "86803714DF3BDE4C19F62E1FF01CE96D3C1D1706"
            },

            CapkData().apply {
                rid = "A000000003"
                index = "99"
                hashInd = "01"
                modul =
                    "AB79FCC9520896967E776E64444E5DCDD6E13611874F3985722520425295EEA4BD0C2781DE7F31CD3D041F565F747306EED62954B17EDABA3A6C5B85A1DE1BEB9A34141AF38FCF8279C9DEA0D5A6710D08DB4124F041945587E20359BAB47B7575AD94262D4B25F264AF33DEDCF28E09615E937DE32EDC03C54445FE7E382777"
                exponent = "03"
                expDate = "202001"
                checkSum = "EE1511CEC71020A9B90443B37B1D5F6E703030F6"

            },

            CapkData().apply {
                rid = "A000000003"
                index = "50"
                hashInd = "01"
                modul =
                    "D11197590057B84196C2F4D11A8F3C05408F422A35D702F90106EA5B019BB28AE607AA9CDEBCD0D81A38D48C7EBB0062D287369EC0C42124246AC30D80CD602AB7238D51084DED4698162C59D25EAC1E66255B4DB2352526EF0982C3B8AD3D1CCE85B01DB5788E75E09F44BE7361366DEF9D1E1317B05E5D0FF5290F88A0DB47"
                exponent = "03"
                expDate = "202001"
                checkSum = "4ABFFD6B1C51212D05552E431C5B17007D2F5E6D"
            },

            CapkData().apply {
                rid = "A000000003"
                index = "92"
                hashInd = "01"
                modul =
                    "996AF56F569187D09293C14810450ED8EE3357397B18A2458EFAA92DA3B6DF6514EC060195318FD43BE9B8F0CC669E3F844057CBDDF8BDA191BB64473BC8DC9A730DB8F6B4EDE3924186FFD9B8C7735789C23A36BA0B8AF65372EB57EA5D89E7D14E9C7B6B557460F10885DA16AC923F15AF3758F0F03EBD3C5C2C949CBA306DB44E6A2C076C5F67E281D7EF56785DC4D75945E491F01918800A9E2DC66F60080566CE0DAF8D17EAD46AD8E30A247C9F"
                exponent = "03"
                expDate = "202001"
                checkSum = "5765295089960938BAAA4431506E424295F98BD0"

            },

            CapkData().apply {
                rid = "A000000003"
                index = "94"
                hashInd = "01"
                modul =
                    "ACD2B12302EE644F3F835ABD1FC7A6F62CCE48FFEC622AA8EF062BEF6FB8BA8BC68BBF6AB5870EED579BC3973E121303D34841A796D6DCBC41DBF9E52C4609795C0CCF7EE86FA1D5CB041071ED2C51D2202F63F1156C58A92D38BC60BDF424E1776E2BC9648078A03B36FB554375FC53D57C73F5160EA59F3AFC5398EC7B67758D65C9BFF7828B6B82D4BE124A416AB7301914311EA462C19F771F31B3B57336000DFF732D3B83DE07052D730354D297BEC72871DCCF0E193F171ABA27EE464C6A97690943D59BDABB2A27EB71CEEBDAFA1176046478FD62FEC452D5CA393296530AA3F41927ADFE434A2DF2AE3054F8840657A26E0FC617"
                exponent = "03"
                expDate = "202001"
                checkSum = "429C954A3859CEF91295F663C963E582ED6EB253"

            },

            CapkData().apply {
                rid = "A000000025"
                index = "C9"
                hashInd = "01"
                modul =
                    "B362DB5733C15B8797B8ECEE55CB1A371F760E0BEDD3715BB270424FD4EA26062C38C3F4AAA3732A83D36EA8E9602F6683EECC6BAFF63DD2D49014BDE4D6D603CD744206B05B4BAD0C64C63AB3976B5C8CAAF8539549F5921C0B700D5B0F83C4E7E946068BAAAB5463544DB18C63801118F2182EFCC8A1E85E53C2A7AE839A5C6A3CABE73762B70D170AB64AFC6CA482944902611FB0061E09A67ACB77E493D998A0CCF93D81A4F6C0DC6B7DF22E62DB"
                exponent = "03"
                expDate = "202001"
                checkSum = "8E8DFF443D78CD91DE88821D70C98F0638E51E49"

            },

            CapkData().apply {
                rid = "A000000025"
                index = "CA"
                hashInd = "01"
                modul =
                    "C23ECBD7119F479C2EE546C123A585D697A7D10B55C2D28BEF0D299C01DC65420A03FE5227ECDECB8025FBC86EEBC1935298C1753AB849936749719591758C315FA150400789BB14FADD6EAE2AD617DA38163199D1BAD5D3F8F6A7A20AEF420ADFE2404D30B219359C6A4952565CCCA6F11EC5BE564B49B0EA5BF5B3DC8C5C6401208D0029C3957A8C5922CBDE39D3A564C6DEBB6BD2AEF91FC27BB3D3892BEB9646DCE2E1EF8581EFFA712158AAEC541C0BBB4B3E279D7DA54E45A0ACC3570E712C9F7CDF985CFAFD382AE13A3B214A9E8E1E71AB1EA707895112ABC3A97D0FCB0AE2EE5C85492B6CFD54885CDD6337E895CC70FB3255E3"
                exponent = "03"
                expDate = "202001"
                checkSum = "6BDA32B1AA171444C7E8F88075A74FBFE845765F"
            }
                          )
}