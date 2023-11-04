import SwiftUI
import shared

struct SearchScreen: View {
    @StateObject var viewModel = SearchScreenViewModel()
    
    let gridColumns: [GridItem] = Array(repeating: GridItem(.flexible() ,spacing: 15), count: 2)
    
    var body: some View {
        NavigationStack {
            ScrollView {
                LazyVGrid(columns: gridColumns, spacing: 15) {
                    if viewModel.isLoading {
                        Section(footer: ProgressView()){}.padding(.vertical, 16)
                    }
                    
                    ForEach(viewModel.products, id: \.id) { product in
                        NavigationLink(value: product){
                            ProductGridItem(product: product)
                                .task {
                                    if product == viewModel.products.last && !viewModel.isLoading && !viewModel.loadFinished {
                                        await viewModel.loadProducts()
                                    }
                                }
                        }
                        .buttonStyle(PlainButtonStyle())
                    }
                }
                .padding(.horizontal, 16)
            }
        }
        .task {
            await viewModel.loadProducts()
        }
        .searchable(text: $viewModel.request, placement: .navigationBarDrawer(displayMode: .always))
        .onSubmit(of: .search) { Task { await viewModel.loadProducts() } }
    }
}
