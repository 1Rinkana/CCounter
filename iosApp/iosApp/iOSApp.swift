import SwiftUI
import shared


@main
struct iOSApp: App {
    var body: some Scene {
        WindowGroup {
            SearchScreen().onAppear { HelperKt.doInitKoin() }
        }
    }
}
